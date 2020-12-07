package Courier;

import javafx.beans.property.*;

import java.sql.Date;

public class Client {
    private SimpleStringProperty name_client;
    private SimpleStringProperty name_package;
    private SimpleStringProperty type_package;
    private SimpleStringProperty status_package;
    private SimpleObjectProperty<Date> registry_date;
    private SimpleObjectProperty<Date> send_date;
    private SimpleStringProperty send_from;
    private SimpleStringProperty deliver_to;
    private SimpleDoubleProperty price_package;

    public Client()
    {
      this.name_package=new SimpleStringProperty();
       this.type_package=new SimpleStringProperty();
       this.status_package=new SimpleStringProperty();
       this.registry_date = new SimpleObjectProperty<>();
       this.send_date = new SimpleObjectProperty<>();
       this.send_from=new SimpleStringProperty();
       this.deliver_to=new SimpleStringProperty();
       this.price_package=new SimpleDoubleProperty();
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
    public String getNameClient()
    {

        return name_client.get();
    }
    public void setNameClient(String namepack)
    {
        this.name_client.set(namepack);
    }
    public StringProperty NameClientProperty()
    {
        return name_client;
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
