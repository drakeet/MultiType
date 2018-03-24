package me.drakeet.multitype.sample.loadmore;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author jiantao
 */
public class LoadMoreDelegate {

  private final LoadMoreSubject loadMoreSubject;

  public LoadMoreDelegate(LoadMoreSubject loadMoreSubject) {
    this.loadMoreSubject = loadMoreSubject;
  }

  /**
   * Should be called after recyclerView setup with its layoutManager and adapter
   *
   * @param recyclerView the RecyclerView
   */
  public void attach(RecyclerView recyclerView) {
    recyclerView.addOnScrollListener(new EndlessScrollListener(loadMoreSubject));
  }

  private static class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private static final int VISIBLE_THRESHOLD = 1;
    private final LoadMoreSubject loadMoreSubject;

    private EndlessScrollListener(LoadMoreSubject loadMoreSubject) {
      this.loadMoreSubject = loadMoreSubject;
    }

    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
      if (dy < 0
          || loadMoreSubject == null
          || loadMoreSubject.isLoading()) {
        return;
      }

      int lastItem = 0;
      RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
      int totalItemCount = layoutManager.getItemCount();
      if (layoutManager instanceof GridLayoutManager) {
        GridLayoutManager gridLayoutManager = ((GridLayoutManager) layoutManager);
        //Position to find the final item of the current LayoutManager
        lastItem = gridLayoutManager.findLastCompletelyVisibleItemPosition();
        if (lastItem == -1) {
          lastItem = gridLayoutManager.findLastVisibleItemPosition();
        }
      } else if (layoutManager instanceof LinearLayoutManager) {
        LinearLayoutManager linearLayoutManager = ((LinearLayoutManager) layoutManager);
        lastItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
        if (lastItem == -1) {
          lastItem = linearLayoutManager.findLastVisibleItemPosition();
        }
      } else if (layoutManager instanceof StaggeredGridLayoutManager) {
        StaggeredGridLayoutManager staggeredGridLayoutManager =
            ((StaggeredGridLayoutManager) layoutManager);
        // since may lead to the final item has more than one StaggeredGridLayoutManager the particularity of the so here that is an array
        // this array into an array of position and then take the maximum value that is the last show the position value
        int[] lastPositions =
            staggeredGridLayoutManager.findLastCompletelyVisibleItemPositions(null);
        lastItem = findMax(lastPositions);
      }
      final int childCount = layoutManager.getChildCount();
      final boolean shouldLoadMore =
          (childCount > 0 && lastItem >= totalItemCount - VISIBLE_THRESHOLD);
      if (shouldLoadMore) {
        loadMoreSubject.onLoadMore();
      }
    }
  }

  private static int findMax(int[] lastPositions) {
    if (lastPositions == null || lastPositions.length <= 0) {
      return 0;
    }
    int max = lastPositions[0];
    for (int value : lastPositions) {
      max = max > value ? max : value;
    }
    return max;
  }

  public interface LoadMoreSubject {
    /**
     * load status
     *
     * @return ture or false
     */
    boolean isLoading();

    /**
     * 加载回调
     */
    void onLoadMore();
  }
}