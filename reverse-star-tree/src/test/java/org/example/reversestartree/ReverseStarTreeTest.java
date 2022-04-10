package org.example.reversestartree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReverseStarTreeTest {

    private final ReverseStarTree reverseStarTree = new ReverseStarTree();

    @Test
    void buildAndGetReverseStarTreeReturnsEmptyStringWhenTreeLevelIsGivenZero() {

        assertEquals("", reverseStarTree.buildAndGetReverseStarTree(0));

    }

    @Test
    void buildAndGetReverseStarTreeReturnsWarningMessageWhenNegativeLevelIsGiven() {

        assertEquals("Please give zero or a positive value for tree level",
                reverseStarTree.buildAndGetReverseStarTree(-5));

    }

    @Test
    void buildAndGetReverseStarTreeSuccessfullyReturnsTreeWhenAPositiveValueIsGiven() {

        assertEquals("***\n * \n",
                reverseStarTree.buildAndGetReverseStarTree(2));

    }



    @Test
    void drawReverseStarTreePrintsWarningMessageOnInputMismatchException() {

        //when integer value given is out of int range
        //or when some other type is given from the console

    }



}