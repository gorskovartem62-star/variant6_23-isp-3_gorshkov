    // ===== 2. Промежуточный абстрактный класс =====
    abstract class DataReport extends Report {
        protected String source;

        public DataReport(String id, String name, String source) {
            super(id, name);
            this.source = source;
        }

        // Реализуем один абстрактный метод
        @Override
        public void print() {
            System.out.println("Печать отчёта: " + name);
        }

        // Новый абстрактный метод
        public abstract void getData();
    }
