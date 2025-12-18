class Report {
    private String type;
    private String date;

    public Report(String type, String date) {
        this.type = type;
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.isEmpty()) {
            throw new IllegalArgumentException("Тип не может быть пустым");
        }
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void show() {
        System.out.println("Отчёт: " + type + ", Дата: " + date);
    }
}