/*
 * Copyright 2016 drakeet. https://github.com/drakeet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.drakeet.multitype;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author drakeet
 */
public class ItemsTest {

    private Items items;
    private ItemContent original;


    @Before
    public void setUp() throws Exception {
        items = new Items();
        original = new TestItemContent("test0");
        items.add(original);
    }


    @Test
    public void contains() throws Exception {
        Items _items = new Items(items.toList());
        assertTrue(_items.contains(original));
    }
}