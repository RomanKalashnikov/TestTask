public class Main {
    private ArgumentsParser containerArg;
    private Comparator comparator;
    private PrepareArgs prepareArgs;
    private Sort sorter;
    public static void main(String[] args) {

        try {
            new Main().run(args);
        } catch (MyEx e) {
            System.err.printf("Program error: %s", e.getMessage());
        }
    }


    private void run(String[] args) {
        containerArg = new ArgumentsParser(args);
        containerArg.parsingArg();
        comparator = new FabricComp().getComp(containerArg.getDataType(), containerArg.getSortType());
        prepareArgs = new PrepareArgs(containerArg.getDataType());
        sorter = new Sort(comparator, containerArg.getInputFiles(), containerArg.getFileResult(), prepareArgs);
        sorter.mergeAllFiles();
    }
}
