package leetcode.p715;

import java.util.ArrayList;

class RangeModule {

    ArrayList<Integer> range;

    public RangeModule() {
        range = new ArrayList<>();
    }

    public void addRange(int left, int right) {
        if (range.isEmpty()) {
            range.add(left);
            range.add(right);
            return;
        }


    }

    public boolean queryRange(int left, int right) {
        if (range.isEmpty()) {
            return false;
        }

        return false;
    }

    public void removeRange(int left, int right) {
        if (range.isEmpty()) {
            return;
        }


    }

    public static void main(String[] args) {
        RangeModule obj = new RangeModule();
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
