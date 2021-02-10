
public class PrepareArgs {
    private static final String KEY_PATTREN_INTEGER = "[^0-9]";
    private static final String KEY_PATTREN_STRING = "[\\d\\W]";
    private DataType dataType;

    PrepareArgs(DataType dataType) {
        this.dataType = dataType;
    }

    Comparable prepare(String s) {
        if (s == null) {
            return null;
        }
        switch (dataType) {
            case INTEGER:
                if (s.matches(KEY_PATTREN_INTEGER)) {
                    System.err.printf("В переданных файлах был найден элемент '%s' " +
                            "Он не будет добавлен в итоговый файл %n", s);
                    return null;
                }
                return Integer.valueOf(s);
            case STRING:
                if (s.matches(KEY_PATTREN_STRING)) {
                    System.err.printf("В переданных файлах был найден элемент '%s' " +
                            "Он не будет добавлен в итоговый файл %n", s);
                    return s.replaceAll(KEY_PATTREN_STRING,"");
                }
                return s;
            default:
                throw new MyEx();
        }
    }
}
