package HW_2;



import java.awt.geom.Point2D;

public class QuickSort {


    /**
     * Default Contructor
     */
    public QuickSort() {
        //Empty constructor --- do nothing
    }

    /**
     * The main function that implements QuickSort
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @param orderBy    --> compareX or compareY
     *                   compareX: sort minimum to maximum arr[] by X point
     *                   compareY: sort minimum to maximum arr[] by Y point
     */
    public void sort(Point2D.Double[] arr, int startIndex, int lastIndex, String orderBy) {
        //Write codes here
        if (orderBy.equals("compareX")) {

            int arraySize = lastIndex - startIndex + 1;

            if ((arraySize>=0) && (arraySize<=3)){

                if (arraySize <= 1){
                    return;
                }

                else if (arraySize == 2) {
                    if (arr[startIndex].getX() > arr[lastIndex].getX()){
                        swap(arr, startIndex, lastIndex);
                    }

                    return;
                } else {
                    if (arr[startIndex].getX() > arr[lastIndex - 1].getX()){
                        swap(arr, startIndex, lastIndex - 1);
                    }

                    if (arr[startIndex].getX() > arr[lastIndex].getX()){
                        swap(arr, startIndex, lastIndex);
                    }

                    if (arr[lastIndex - 1].getX() > arr[lastIndex].getX()){
                        swap(arr, lastIndex - 1, lastIndex);
                    }

                }
            }
            int partitionX = partitionX(arr, startIndex, lastIndex);
            sort(arr, startIndex, partitionX - 1, orderBy);
            sort(arr, partitionX + 1, lastIndex, orderBy);

        }


        if (orderBy.equals("compareY")) {

            int arraySize = lastIndex - startIndex + 1;

            if ((arraySize>=0)&&(arraySize<=3)){

                if (arraySize <= 1)
                    return;

                else if(arraySize == 2) {
                    if (arr[startIndex].getY() > arr[lastIndex].getY())
                        swap(arr, startIndex, lastIndex);
                    return;
                }

                else {

                    if (arr[startIndex].getY() > arr[lastIndex - 1].getY()){
                        swap(arr, startIndex, lastIndex - 1);
                    }

                    if (arr[startIndex].getY() > arr[lastIndex].getY()) {
                        swap(arr, startIndex, lastIndex);
                    }

                    if (arr[lastIndex - 1].getY() > arr[lastIndex].getY()) {
                        swap(arr, lastIndex - 1, lastIndex);
                    }
                }
            }

            int partitionY = partitionY(arr, startIndex, lastIndex);
            sort(arr, startIndex, partitionY - 1, orderBy);
            sort(arr, partitionY + 1, lastIndex, orderBy);
        }
    }//complete

    /**
     * A utility function to swap two elements
     *
     * @param arr --> Array to be sorted
     * @param i   --> first index
     * @param j   --> second index
     */
    private void swap(Point2D.Double[] arr, int i, int j) {
        //Write codes here
        Point2D.Double temporary = arr[i];
        arr[i] = arr[j];
        arr[j] = temporary;
    }//complete

    /**
     * Get Median of 3 order by Point.X
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianX(Point2D.Double[] arr, int left, int right) {
        //Write codes here

        /*
         * create subarray with low, high, and middle elements in the array sort the
         * subarray and use index 1 as the median of 3
         */

        int centerX = (left + right) / 2;

        if (arr[left].getX() > arr[centerX].getX()){
            swap(arr, left, centerX);
        }


        if (arr[left].getX() > arr[right].getX()){
            swap(arr, left, right);
        }

        if (arr[centerX].getX() > arr[right].getX()){
            swap(arr, centerX, right);
        }


        swap(arr, centerX, right - 1);
        return arr[right - 1];

    }//complete

    /**
     * Get Median of 3 order by Point.Y
     *
     * @param arr   --> Array to be sorted
     * @param left  --> First index of arr[]
     * @param right --> Last index of arr[]
     * @return
     */
    private Point2D.Double getMedianY(Point2D.Double[] arr, int left, int right) {
        //Write codes here

        int centerY = (left + right) / 2;

        if (arr[left].getY() > arr[centerY].getY()){
            swap(arr, left, centerY);
        }


        if (arr[left].getY() > arr[right].getY()){
            swap(arr, left, right);
        }

        if (arr[centerY].getY() > arr[right].getY()){
            swap(arr, centerY, right);
        }


        swap(arr, centerY, right - 1);
        return arr[right - 1];

    }//complete

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.X
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionX(Point2D.Double[] arr, int startIndex, int lastIndex) {
        //Write codes here

        double pivotX = getMedianX(arr, startIndex, lastIndex).getX();
        int i = startIndex;
        int j = lastIndex-1;

        while (true) {
            while (arr[++i].getX() < pivotX) ;
            while (arr[--j].getX() > pivotX) ;
            if (i < j) {
                swap(arr,i,j);
            } else break;
        } //end-while

        swap(arr,i,lastIndex-1);// Restore the pivot
        return i;
    }//complete

    /**
     * This function takes median of three as pivot, places
     * the pivot element at the end of the sorted
     * array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     * Sort order by Point.Y
     *
     * @param arr        --> Array to be sorted
     * @param startIndex --> First index of arr[]
     * @param lastIndex  --> Last index of arr[]
     * @return pivot index
     */
    private int partitionY(Point2D.Double[] arr, int startIndex, int lastIndex) {
        //Write codes here

        double pivotY = getMedianY(arr, startIndex, lastIndex).getY();
        int i = startIndex;
        int j = lastIndex-1;
        while (true) {
            while (arr[++i].getY() < pivotY) ;
            while (arr[--j].getY() > pivotY) ;
            if (i < j) {
                swap(arr,i,j);
            } else break;
        } //end-while

        swap(arr,i,lastIndex-1);// Restore the pivot
        return i;

    }//complete

}
