
public class IntComp extends Comparator<Integer> {
    private SortType sortType;

    IntComp(SortType sortType) {
        super();
        this.sortType = sortType;
    }

    @Override
    int compare(Integer a, Integer b) {
        switch (sortType) {
            case DESC:
                return b.compareTo(a);
            case ASC:
                return a.compareTo(b);
                default:throw new MyEx();
        }
    }

}
