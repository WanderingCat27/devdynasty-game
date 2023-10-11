package ui;

import ui.Container.UIContainer;

public enum Anchor {
    TOP_LEFT
    // function that takes in a container and produces a point
    {
        @Override
        public int getXOffset(UIContainer c) {
            return 0;
        }

        @Override
        public int getYOffset(UIContainer c) {
            return 0;
        }
    },

     TOP_CENTER {

        @Override
        public int getXOffset(UIContainer c) {
            return -c.getWidth() / 2;
        }

        @Override
        public int getYOffset(UIContainer c) {
            return 0;
        }

    },

    TOP_RIGHT {

        @Override
        public int getXOffset(UIContainer c) {
            return -c.getWidth();
        }

        @Override
        public int getYOffset(UIContainer c) {
            return 0;
        }

    },

    CENTER_LEFT {

        @Override
        public int getXOffset(UIContainer c) {
            return 0;
        }

        @Override
        public int getYOffset(UIContainer c) {
            // TODO Auto-generated method stub
            return -c.getHeight()/2;
        }

    },


    CENTER {

        @Override
        public int getXOffset(UIContainer c) {
            return -c.getWidth() / 2;
        }

        @Override
        public int getYOffset(UIContainer c) {
            return -c.getHeight() / 2;
        }

    },

    CENTER_RIGHT {
        @Override
        public int getXOffset(UIContainer c) {
            return -c.getWidth();
        }

        @Override
        public int getYOffset(UIContainer c) {
            return -c.getHeight()/2;
        }
    },

    BOTTOM_LEFT {
        @Override
        public int getXOffset(UIContainer c) {
            return 0;
        }

        @Override
        public int getYOffset(UIContainer c) {
            return -c.getHeight();
        }
    },

    BOTTOM_CENTER {
        @Override
        public int getXOffset(UIContainer c) {
            return -c.getWidth()/2;
        }

        @Override
        public int getYOffset(UIContainer c) {
            return -c.getHeight();
        }
    },

    BOTTOM_RIGHT
 {
        @Override
        public int getXOffset(UIContainer c) {
            return -c.getWidth();
        }

        @Override
        public int getYOffset(UIContainer c) {
            // TODO Auto-generated method stub
            return -c.getHeight();
        }
    }
    ;
    
    public abstract int getXOffset(UIContainer c);

    public abstract int getYOffset(UIContainer c);
}
