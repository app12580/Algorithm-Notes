import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {

    }

    public ListNode(int x) {
        val = x;
        next = null;
    }

    public static ListNode makeListNode(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode next = null;
        ListNode cur = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            cur = new ListNode(arr[i]);
            cur.next = next;
            next = cur;
        }
        return cur;
    }

    @Override
    public String toString() {
        List<Integer> list = new ArrayList<>();
        ListNode cur = this;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return list.toString();
    }


}
