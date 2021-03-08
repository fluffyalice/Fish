//username:acb19rx
import sheffield.*;
public class Fish {
	public static void main(String args[]) {
        EasyReader fileInput = new EasyReader("fish.txt");
        int HEIGHT = 45;//The size of small fish screen
        int WIDTH = 134;
        int RANGE_SMALL = 5;//The range of the small fish
        int RANGE_BIG = 450;
        int CANVAS_HEIGHT = 500;//The size of the biggest screen
        int CANVAS_WIDTH = 1200;
        int BIG_FISH = 4;//4 times as big as small fish
        int newHeight = HEIGHT * BIG_FISH;//The size of the big fish
        int newWidth = WIDTH * BIG_FISH;
        int ROW_BIG_FISH = 150;//The position of the big fish
        int COLUMN_BIG_FISH = 668;

        /**
         * Draw and color the biggest screen
         */
        EasyGraphics g = new EasyGraphics(CANVAS_WIDTH,CANVAS_HEIGHT);
        g.setColor(0,0,0);
        g.fillRectangle(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);

        //convert file into unicode and put it into array
        String characters = fileInput.readString();
        char[] charArray = characters.toCharArray();
        int convertedUnicode;
        int[] unicodeArray = new int[WIDTH*HEIGHT];
        for(int i=0; i<charArray.length; i++){
            convertedUnicode = (int)(charArray[i]);
            unicodeArray[i] = convertedUnicode;
        }

        int tempUnicode = 0;
        int colorRange = 255;//The range of the small fishes' color
        int refPointX;
        int refPointY;
        int red;
        int green;
        int blue;
        int leftMargin = RANGE_SMALL+WIDTH;
        int bottomMargin = RANGE_SMALL+HEIGHT;

        /**
         * convert 1d array to 2d array
         */
        int tempUnicode1;
        int counter4 = 0;
        int [][] unicode2dArray = new int[WIDTH][HEIGHT];
        for(int i=HEIGHT-1; i>=0; i--){
            for(int j=WIDTH-1; j>=0; j--){
                tempUnicode1 = unicodeArray[counter4];
                unicode2dArray[j][i] = tempUnicode1;
                counter4 = counter4 + 1;
            }
        }

        /**
         * draw the big fish
         */
        int tempI;
        int tempJ;
        int tempUnicode2;
        int newRefPointY = ROW_BIG_FISH+newHeight;
        int newRefPointX = COLUMN_BIG_FISH+newWidth;
        for(int i=newRefPointY-1; i>=newRefPointY-newHeight; i--){
            for(int j=newRefPointX-1; j>=newRefPointX-newWidth; j--){
                tempI = (int)((i-newRefPointY+newHeight) / 4);
                tempJ = (int)((j-newRefPointX+newWidth) / 4);
                tempUnicode2 = unicode2dArray[tempJ][tempI];
                if(tempUnicode2%2 == 0){
                    g.setColor(255,255,255);
                    g.plot(j,i);
                }
            }
        }

        /**
         * Draw 20 small fishes heading left
         */
        int counter1 = 20;//The number of the fish
        int counter2;

        while(counter1>0) {
            refPointX = (int) (Math.random() * RANGE_BIG)+RANGE_SMALL;
            refPointY = (int) (Math.random() * RANGE_BIG)+RANGE_SMALL+HEIGHT;
            counter2 = 0;
            red = (int) (Math.random() * colorRange);
            green = (int) (Math.random() * colorRange);
            blue = (int) (Math.random() * colorRange);

            for (int j=refPointY; j>refPointY-HEIGHT; j--) {
                for (int k=refPointX; k<refPointX+WIDTH; k++) {
                    tempUnicode = unicodeArray[counter2++];
                    if (tempUnicode % 2 == 0) {
                        g.setColor(red, green, blue);
                        g.plot(k, j);
                    }
                }
            }
            counter1 = counter1 - 1;
        }

        // wait 3 seconds
        g.waitSeconds(3);

        // reset the left half of the canvas
        g.setColor(0,0,0);
        g.fillRectangle(0, 0, (int)(0.5*CANVAS_WIDTH), CANVAS_HEIGHT);

        /**
         * Draw 20 small fishes heading right
         */
        counter1 = 20;   // reset counter1
        while(counter1>0) {
            refPointX = (int) (Math.random() * RANGE_BIG) + leftMargin;
            refPointY = (int) (Math.random() * RANGE_BIG) + bottomMargin;
            counter2 = 0;
            red = (int) (Math.random() * colorRange);
            green = (int) (Math.random() * colorRange);
            blue = (int) (Math.random() * colorRange);

            for (int j=refPointY; j>refPointY-HEIGHT; j--) {
                for (int k=refPointX; k>refPointX-WIDTH; k--) {
                    tempUnicode = unicodeArray[counter2++];
                    if (tempUnicode % 2 == 0) {
                        g.setColor(red, green, blue);
                        g.plot(k, j);
                    }
                }
            }
            counter1 = counter1 - 1;
        }

    }
}