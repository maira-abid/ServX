package pl.servx.servx.Model;

public class service_item {
    public String oil;
    public String wash;
    public String location;
    public String date;
    public String time;
    public String status;


    public service_item() {
    }

    public service_item(String oil, String wash, String location, String date, String time, String status) {
        this.oil = oil;
        this.wash = wash;
        this.location = location;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getOil() {
        return oil;
    }

    public void setOil(String oil) {
        this.oil = oil;
    }

    public String getWash() {
        return wash;
    }

    public void setWash(String wash) {
        this.wash = wash;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
