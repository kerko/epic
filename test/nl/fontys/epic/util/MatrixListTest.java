/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.fontys.epic.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class MatrixListTest {
        
        // ===========================================================
        // Definitions
        // ===========================================================
        
        MatrixList<Indexable> matrixList;
        
        Indexable indexable;
        
        final int X = 10, Y = 10;
        
        // ===========================================================
        // Setup
        // ===========================================================

        /**
         * @throws java.lang.Exception
         */
        @Before
        public void setUp() throws Exception {
                matrixList = new MatrixList<>();
                indexable = new Indexable() {

                        @Override
                        public int getX() {
                                return X;
                        }

                        @Override
                        public int getY() {
                                return Y;
                        }
                        
                };
        }
        
        // ===========================================================
        // Test cases
        // ===========================================================

        /**
         * Test method for {@link de.myreality.chunx.util.MatrixList#remove(int, int)}.
         */
        @Test
        public void testRemoveIntInt() {
                matrixList.add(indexable);
                assertTrue("Element can't be removed.", matrixList.remove(X, Y));
        }

        /**
         * Test method for {@link de.myreality.chunx.util.MatrixList#copy()}.
         */
        @Test
        public void testCopy() {
                matrixList.add(indexable);
                MatrixList<Indexable> copy = matrixList.copy();
                
                assertTrue("The indexable must be part of the copy", copy.contains(indexable));
                assertTrue("The index must be part of the copy", copy.contains(X,  Y));
                
                matrixList.clear();
                
                assertFalse("The copy shouldn't be affected by its origin.", copy.isEmpty());
        }

        /**
         * Test method for {@link de.myreality.chunx.util.MatrixList#contains(int, int)}.
         */
        @Test
        public void testContainsIntInt() {
                matrixList.add(indexable);
                
                assertTrue("The index should be in the list", matrixList.contains(X, Y));
        }

        /**
         * Test method for {@link de.myreality.chunx.util.MatrixList#get(int, int)}.
         */
        @Test
        public void testGet() {
                matrixList.add(indexable);
                
                Indexable result = matrixList.get(X, Y);
                Indexable wrongResult = matrixList.get(X + 1, Y);
                
                assertTrue("The result should be the same", indexable.equals(result));
                assertTrue("The wrong result has to be null", wrongResult == null);
        }

        /**
         * Test method for {@link de.myreality.chunx.util.MatrixList#set(de.myreality.chunx.util.MatrixList)}.
         */
        @Test
        public void testSet() {
                MatrixList<Indexable> source = new MatrixList<>();
                source.add(indexable);
                matrixList.set(source);
                
                assertFalse("Matrix list shoudn't be empty", matrixList.isEmpty());
                assertTrue("Matrix list should contain the element", matrixList.contains(indexable));
        }

        /**
         * Test method for {@link java.util.Collection#size()}.
         */
        @Test
        public void testSize() {
                assertTrue("Size has to be 0 here", matrixList.isEmpty());
                matrixList.add(indexable);                
                assertTrue("Size has to be 1 here", matrixList.size() == 1);
                matrixList.add(indexable);
                assertTrue("Size has to be 1 here", matrixList.size() == 1);
        }

        /**
         * Test method for {@link java.util.Collection#isEmpty()}.
         */
        @Test
        public void testIsEmpty() {
                assertTrue("MatrixList has to be empty here", matrixList.isEmpty());
                matrixList.add(indexable);                
                assertFalse("MatrixList shouldn't be empty here", matrixList.isEmpty());
        }

        /**
         * Test method for {@link java.util.Collection#contains(java.lang.Object)}.
         */
        @Test
        public void testContainsObject() {
                assertFalse("Object shouldn't be in the object", matrixList.contains(indexable));
                matrixList.add(indexable);                
                assertTrue("Object should exist in the object", matrixList.contains(indexable));
        }

        /**
         * Test method for {@link java.util.Collection#toArray()}.
         */
        @Test
        public void testToArray() {
                matrixList.add(indexable);        
                Indexable[] arr = new Indexable[matrixList.size()];
                arr = matrixList.toArray(arr);
                
                assertTrue("Array shouldn't be null", arr != null);
                assertTrue("Array length has to be 1", arr.length == 1);
                assertTrue("First element has to be the indexable", arr[0].equals(indexable));
        }

        /**
         * Test method for {@link java.util.Collection#remove(java.lang.Object)}.
         */
        @Test
        public void testRemoveObject() {
                matrixList.add(indexable);
                matrixList.remove(indexable);
                assertTrue("Matrix list has to be empty", matrixList.isEmpty());
        }
}