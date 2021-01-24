package Courier;

import javafx.beans.property.*;

import java.sql.Timestamp;
import java.text.ParseException;

public class Admin {
    private SimpleIntegerProperty id_office;
    private SimpleStringProperty location_office;
    private SimpleObjectProperty<Timestamp> open_office;
    private SimpleObjectProperty<Timestamp> close_office;
    private SimpleIntegerProperty id_courier;
    private SimpleStringProperty egn_courier;
    private SimpleStringProperty name_courier;
    private SimpleStringProperty name_client;
    private SimpleStringProperty phone_client;
    private SimpleStringProperty phone_courier;
    private SimpleIntegerProperty brPolucheni;
    private SimpleIntegerProperty brNePolucheni;
    private SimpleIntegerProperty brOtkazani;
    private SimpleIntegerProperty brCouriers;
    private SimpleIntegerProperty brClients;
    private SimpleDoubleProperty Percent;
    public Admin()
    {
            this.name_client=new SimpleStringProperty();
            this.phone_client=new SimpleStringProperty();
            this.id_office=new SimpleIntegerProperty();
            this.location_office=new SimpleStringProperty();
            this.open_office = new SimpleObjectProperty<>();
            this.close_office = new SimpleObjectProperty<>();
            this.id_courier = new SimpleIntegerProperty();
            this.egn_courier=new SimpleStringProperty();
            this.name_courier=new SimpleStringProperty();
            this.phone_courier=new SimpleStringProperty();
            this.brPolucheni=new SimpleIntegerProperty();
            this.brNePolucheni=new SimpleIntegerProperty();
            this.brOtkazani=new SimpleIntegerProperty();
            this.Percent=new SimpleDoubleProperty();
            this.brCouriers=new SimpleIntegerProperty();
           this.brClients=new SimpleIntegerProperty();
    }
    public String name_client()
    {
        return name_client.get();
    }
    public void setNameClient(String nameclient)
    {
        this.name_client.set(nameclient);
    }
    public StringProperty ClientNameProperty()
    {
        return name_client;
    }
    public String phone_client()
    {
        return phone_client.get();
    }
    public void setPhoneClient(String phoneclient)
    {
        this.phone_client.set(phoneclient);
    }
    public StringProperty ClientPhoneProperty()
    {
        return phone_client;
    }
    public String getNameCourier()
    {

        return name_courier.get();
    }
    public void setNameCourier(String nameCourier)
    {
        this.name_courier.set(nameCourier);
    }
    public StringProperty NameCourierProperty()
    {
        return name_courier;
    }
    public String getLocationOffice()
    {

        return location_office.get();
    }
    public void setLocationOffice(String locationOffice)
    {
        this.location_office.set(locationOffice);
    }
    public StringProperty LocationOfficeProperty()
    {
        return location_office;
    }
    public int getOfficeId()
    {

        return id_office.get();
    }
    public void setOfficeId(int officeid)
    {
        this.id_office.set(officeid);
    }
    public IntegerProperty OfficeIdProperty()
    {
        return id_office;
    }
    public int getCourierId()
    {

        return id_courier.get();
    }
    public void setCourierId(int courierId)
    {
        this.id_courier.set(courierId);
    }
    public IntegerProperty CourierIdProperty()
    {
        return id_courier;
    }
    public String getPhoneCourier()
    {

        return phone_courier.get();
    }
    public void setPhoneCourier(String phoneCourier)
    {
        this.phone_courier.set(phoneCourier);

    }
    public StringProperty PhoneCourierProperty()
    {
        return phone_courier;
    }
    public String getEgnCourier()
    {

        return egn_courier.get();
    }
    public void setEgnCourier(String egncourier)
    {
        this.egn_courier.set(egncourier);
    }
    public StringProperty EgnCourierProperty()
    {
        return egn_courier;
    }
    public Object getOpenTime(){
        return open_office.get();
    }

    public void setOpenOffice(Timestamp openoffice) throws ParseException {
        this.open_office.set(openoffice);
    }

    public SimpleObjectProperty<Timestamp> OpenOfficeProperty(){
        return open_office;
    }
    public Object getCloseTime(){
        return close_office.get();
    }

    public void setCloseOffice(Timestamp closeoffice) throws ParseException {
        this.close_office.set(closeoffice);
    }
    public SimpleObjectProperty<Timestamp> CloseOfficeProperty(){
        return close_office;
    }
    public int getPolucheniBr()
    {

        return brPolucheni.get();
    }
    public void setPolucheniBr(int polucheniBr)
    {
        this.brPolucheni.set(polucheniBr);
    }
    public IntegerProperty PolucheniBrProperty()
    {
        return brPolucheni;
    }
    public int getNePolucheniBr()
    {

        return brNePolucheni.get();
    }
    public void setNePolucheniBr(int NepolucheniBr)
    {
        this.brNePolucheni.set(NepolucheniBr);
    }
    public IntegerProperty NePolucheniBrProperty()
    {
        return brNePolucheni;
    }
    public int getOtkazaniBr()
    {

        return brOtkazani.get();
    }
    public void setOtkazaniBr(int OtkazaniBr)
    {
        this.brOtkazani.set(OtkazaniBr);
    }
    public IntegerProperty OtkazaniBrProperty()
    {
        return brOtkazani;
    }
    public int getBrCouriers()
    {

        return brCouriers.get();
    }
    public void setBrCouriers(int OtkazaniBr)
    {
        this.brCouriers.set(OtkazaniBr);
    }
    public IntegerProperty BrCouriersProperty()
    {
        return brCouriers;
    }
    public int getBrClients()
    {

        return brClients.get();
    }
    public void setBrClients(int OtkazaniBr)
    {
        this.brClients.set(OtkazaniBr);
    }
    public IntegerProperty BrClientsProperty()
    {
        return brClients;
    }
    public double getPercent()
    {

        return Percent.get();
    }
    public void setPercent(double percent)
    {
        this.Percent.set(percent);
    }
    public DoubleProperty PercentProperty()
    {
        return Percent;
    }
}
