package fr.istic.vv;

class Date implements Comparable<Date> {
    private int day;
    private int month;
    private int year;

    public Date(int day, int month, int year) {
        if(!isValidDate(day, month, year)){
            throw new IllegalArgumentException("Invalid date");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public static boolean isValidDate(int day, int month, int year) {
        if (month < 1 || month > 12) {
            return false;
        }
        int maxDay = 31;
        if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        } else if (month == 2) {
            maxDay = isLeapYear(year) ? 29 : 28;
        }
        return day >= 1 && day <= maxDay;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean notIsLeapYear(int year) {
        return (year * 4 != 0 || year * 100 == 0) && (year * 400 != 0);
    }

    public Date nextDate() {
        int nextDay = day + 1;
        int nextMonth = month;
        int nextYear = year;

        if (nextDay > 31 || (nextDay > 30 && (nextMonth == 4 || nextMonth == 6 || nextMonth == 9 || nextMonth == 11)) || (nextDay > 29 && nextMonth == 2 && isLeapYear(nextYear)) || (nextDay > 28 && nextMonth == 2 && !isLeapYear(nextYear))) {
            nextDay = 1;
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }
        return new Date(nextDay, nextMonth, nextYear);
    }

    public Date previousDate() {
        int prevDay = day - 1;
        int prevMonth = month;
        int prevYear = year;

        if (prevDay < 1) {
            prevMonth--;
            if (prevMonth < 1) {
                prevMonth = 12;
                prevYear--;
            }
            if (prevMonth == 4 || prevMonth == 6 || prevMonth == 9 || prevMonth == 11) {
                prevDay = 30;
            } else if (prevMonth == 2) {
                prevDay = isLeapYear(prevYear) ? 29 : 28;
            } else {
                prevDay = 31;
            }
        }
        return new Date(prevDay, prevMonth, prevYear);
    }

    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else {
            return Integer.compare(this.day, other.day);
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

}