package Courier;

import javafx.beans.property.*;

import java.sql.Date;
public class Courier {
    private SimpleIntegerProperty id_client;
    private SimpleIntegerProperty id_pack;
    private SimpleStringProperty name_client;
    private SimpleStringProperty egn_client;
    private SimpleStringProperty phone_client;
    private SimpleStringProperty name_package;
    private SimpleIntegerProperty id_type;
    private SimpleIntegerProperty id_status;
    private SimpleIntegerProperty id_info;
    private SimpleIntegerProperty id_Registry;
    private SimpleStringProperty type_package;
    private SimpleStringProperty status_package;
    private SimpleObjectProperty<Date> registry_date;
    private SimpleObjectProperty<Date> send_date;
    private SimpleStringProperty send_from;
    private SimpleStringProperty deliver_to;
    private SimpleDoubleProperty price_package;
   public Courier()
   {
       this.id_client=new SimpleIntegerProperty();
       this.name_client=new SimpleStringProperty();
       this.egn_client=new SimpleStringProperty();
       this.phone_client=new SimpleStringProperty();
       this.name_package=new SimpleStringProperty();
       this.type_package=new SimpleStringProperty();
       this.status_package=new SimpleStringProperty();
       this.registry_date = new SimpleObjectProperty<>();
       this.send_date = new SimpleObjectProperty<>();
       this.send_from=new SimpleStringProperty();
       this.deliver_to=new SimpleStringProperty();
       this.price_package=new SimpleDoubleProperty();
   }
   public int getClientId()
   {

       return id_client.get();
   }
   public void setClientId(int clientid)
   {
       this.id_client.set(clientid);
   }
   public IntegerProperty ClientIdProperty()
   {
       return id_client;
   }
    public int getRegistryId()
    {

        return id_Registry.get();
    }
    public void setRegistryId(int registryId)
    {
        this.id_Registry.set(registryId);
    }
    public IntegerProperty RegistryIdProperty()
    {
        return id_Registry;
    }
    public int getPackageId()
    {

        return id_pack.get();
    }
    public void setPackagetId(int packid)
    {
        this.id_pack.set(packid);
    }
    public IntegerProperty PackageIdProperty()
    {
        return id_pack;
    }
    public int getTypeId()
    {

        return id_type.get();
    }
    public void setTypeId(int typeid)
    {
        this.id_type.set(typeid);
    }
    public IntegerProperty TypeIdProperty()
    {
        return id_type;
    }
    public int getStatusId()
    {

        return id_status.get();
    }
    public void setStatusId(int statusid)
    {
        this.id_status.set(statusid);
    }
    public IntegerProperty StatusIdProperty()
    {
        return id_status;
    }
    public int getInfoId()
    {

        return id_info.get();
    }
    public void setInfoId(int infoid)
    {
        this.id_info.set(infoid);
    }
    public IntegerProperty InfoIdProperty()
    {
        return id_info;
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
    public String egn_client()
    {
        return egn_client.get();
    }
    public void setEgnClient(String egnclient)
    {
         this.egn_client.set(egnclient);
    }
    public StringProperty ClientEgnProperty()
    {
        return egn_client;
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
    public String getNamepack()
    {

        return name_package.get();
    }
    public void setNamePack(String namepack)
    {
        this.name_package.set(namepack);
    }
    public StringProperty NamePackProperty()
    {
        return name_package;
    }
    public String getTypepack()
    {

        return type_package.get();
    }
    public void setTypePack(String typepack)
    {
        this.type_package.set(typepack);
    }
    public StringProperty TypePackProperty()
    {
        return type_package;
    }
    public String getStatuspack()
    {

        return status_package.get();
    }
    public void setStatus_package(String typepack)
    {
        this.status_package.set(typepack);
    }
    public StringProperty StatusPackProperty()
    {
        return status_package;
    }
    public Object getRegistryDate(){
        return registry_date.get();
    }

    public void setRegistryDate(Date regDate){
        this.registry_date.set(regDate);
    }

    public SimpleObjectProperty<Date> RegistryDateProperty(){
        return registry_date;
    }
    public Object getSendDate(){
        return send_date.get();
    }

    public void setSendDate(Date sendDate){
        this.send_date.set(sendDate);
    }

    public SimpleObjectProperty<Date> SendDateProperty(){
        return send_date;
    }
    public String getSendpack()
    {

        return send_from.get();
    }
    public void setSendPack(String namepack)
    {
        this.send_from.set(namepack);
    }
    public StringProperty SendPackProperty()
    {
        return send_from;
    }
    public String getDeliverpack()
    {

        return deliver_to.get();
    }
    public void setDeliverPack(String namepack)
    {
        this.deliver_to.set(namepack);
    }
    public StringProperty DeliverPackProperty()
    {
        return deliver_to;
    }
    public Double getPricepack()
    {
        return price_package.get();
    }
    public void setPricePack(Double pricepack)
    {
        this.price_package.set(pricepack);
    }
    public DoubleProperty PricePackProperty()
    {
        return price_package;
    }
}
