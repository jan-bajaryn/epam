package by.epam.task6.sorting.service;

public class InsertBinarySort implements Sort {

    @Override
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }


        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                insertToPrev(arr, 0, i - 1, i);
            }
        }
    }

    private void insertToPrev(int[] arr, int left, int right, int index) {

//3,4,2,3,6,4,5,4
//1,2,3,4,5,6,7
//4.5,10, 0,2

//если наше значение меньше или равно данному элементу, то мы проверяем больше или равно ли оно предыдущему
//если нет, то проверяем левую часть
//если не меньше или равна, то проверяем правую часть
//если не меньше и не равна ни одному из элементов, то вставляем в конец
// если у нас элемент один, то надо найти позицию для вставки(справа или слева)-- справа оно уже находится

        if (left == right && arr[index] <= arr[left]) {
                if (left != 0) {

                    if (arr[index] >= arr[left - 1]) {
                        insert(arr, index, left);
                        return;
                    } else {
                        return;
                    }
                } else {
                    insert(arr, index, 0);
                    return;
                }
            }


            int border = (left + right) / 2;


            if (arr[index] <= arr[border]) {
                if (border == 0) {
                    insert(arr, index, 0);
                    return;
                }
                if (arr[index] >= arr[border - 1]) {
                    insert(arr, index, border);
                } else {
                    insertToPrev(arr, left, border - 1, index);
                }
            } else {
                insertToPrev(arr, border + 1, right, index);
            }

            //1,2,3,4,5,6,7


//        if (left <= right) {
//            int border = (right + left) / 2;
//
//            if (border == 0) {
//                if (arr[index] <= arr[0]) {
//                    insert(arr, index, 0);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//
//
//            if (arr[index] >= arr[border - 1] && arr[index] <= arr[border]) {
//                insert(arr, index, border);
//                return true;
//            }
//            if (left < right){
//                if (!insertToPrev(arr, left, border, index)) {
//                    return insertToPrev(arr, border, right, index);
//                }
//            }
//        }
//
//        return false;
//
//        for (int i = 0; i < right; i++) {
//            if (arr[i] >= arr[right]) {
//                insert(arr, right, i);
//                return;
//            }
//        }
        }

        private void insert ( int[] arr, int indexFrom, int indexTo){
            int temp = arr[indexFrom];
            for (int i = indexFrom; i > indexTo; i--) {
                arr[i] = arr[i - 1];
            }
            arr[indexTo] = temp;
        }
//    @Override
//    public void sort(int[] arr) {
//
//        if (arr == null || arr.length < 2) {
//            return;
//        }
//
//        // от второго элемента до последнего
//        for (int i = 1; i < arr.length; i++) {
//            int tmp = arr[i];
//            int left = 0;
//            int right = i - 1;
//            int border = i;
//            // Ищем позицию для элемента на  i позиции.
//            while (left < right) {
//                border = (left + right) / 2;
//                if (arr[border] < tmp) {
//                    left = border + 1;
//
//                } else {
//                    right = border;
//                }
//            }
//
//            for (int j = i; j > border; j--) {
//                arr[j] = arr[j - 1];
//            }
//            arr[border] = tmp;
//
//        }
//    }
    }
