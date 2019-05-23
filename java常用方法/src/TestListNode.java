import org.junit.Test;

public class TestListNode {
    @Test
    public void test1() {
        Integer[] arr = new Integer[]{1, 3, 5, 7, 3, 4, 2};
        ListNode listNode = ListNode.makeListNode(arr);
        System.out.println(listNode);
    }
}
