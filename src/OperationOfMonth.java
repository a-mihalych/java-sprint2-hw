public class OperationOfMonth {

    private String itemName;
    private boolean isExpense;
    private int quantity;
    private int sumOfOne;

    public OperationOfMonth(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }
    public boolean isExpense() {
        return isExpense;
    }
    public int getQuantity() {
        return quantity;
    }
    public int getSumOfOne() {
        return sumOfOne;
    }
}
