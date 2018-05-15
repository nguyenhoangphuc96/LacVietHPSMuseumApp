package vn.com.lacviet.lacviethpsmuseummanagementapp.model;

import android.support.annotation.Nullable;

import java.util.Date;

public class ExhibitModels {
    int ExhID;
    String ExhibitName;
    String OtherName;
    String CodeID;
    int Number;
    String Element;
    int MaterialType;
    String MaterialName;
    Date VerificationDate;
    String Ages;
    int Expertise;
    String ExpertiseName;
    Date ExpertiseDate;
    int Source;
    String SourceName;
    String ResourceNote;
    int Time;
    String TimeName;
    String TimeNote;
    String LocationID;
    String LacationNote;
    int Owner;
    String OwnerName;
    String OwnerAddress;
    int SecretLevel;
    String SecretLevelName;
    int Stuff;
    String StuffName;
    String DescPhysics;
    String Description;
    String Color;
    int MakeTech;
    String MakeTechName;
    int Maintenance;
    String MaintenenceName;
    String Relics;
    String Vendor;
    int Cost;
    String VendorAddress;
    String Content;
    int Field;
    String FieldName;
    int Collector;
    String CollectorName;
    Date CollectorDate;
    //
    int Image;
    String ExhibitSee;
    String ExhibitHeart;



    public ExhibitModels() {
    }

    public ExhibitModels(int exhID, String exhibitName, String description, int image, String exhibitSee, String exhibitHeart) {
        ExhID = exhID;
        ExhibitName = exhibitName;
        Description = description;
        Image = image;
        ExhibitSee = exhibitSee;
        ExhibitHeart = exhibitHeart;
    }

    public ExhibitModels(int exhID, String exhibitName, String otherName, String codeID, int number, String element, int materialType, String materialName, Date verificationDate, String ages, int expertise, String expertiseName, Date expertiseDate, int source, String sourceName, String resourceNote, int time, String timeName, String timeNote, String locationID, String lacationNote, int owner, String ownerName, String ownerAddress, int secretLevel, String secretLevelName, int stuff, String stuffName, String descPhysics, String description, String color, int makeTech, String makeTechName, int maintenance, String maintenenceName, String relics, String vendor, int cost, String vendorAddress, String content, int field, String fieldName, int collector, String collectorName, Date collectorDate, int image, String exhibitSee, String exhibitHeart) {
        ExhID = exhID;
        ExhibitName = exhibitName;
        OtherName = otherName;
        CodeID = codeID;
        Number = number;
        Element = element;
        MaterialType = materialType;
        MaterialName = materialName;
        VerificationDate = verificationDate;
        Ages = ages;
        Expertise = expertise;
        ExpertiseName = expertiseName;
        ExpertiseDate = expertiseDate;
        Source = source;
        SourceName = sourceName;
        ResourceNote = resourceNote;
        Time = time;
        TimeName = timeName;
        TimeNote = timeNote;
        LocationID = locationID;
        LacationNote = lacationNote;
        Owner = owner;
        OwnerName = ownerName;
        OwnerAddress = ownerAddress;
        SecretLevel = secretLevel;
        SecretLevelName = secretLevelName;
        Stuff = stuff;
        StuffName = stuffName;
        DescPhysics = descPhysics;
        Description = description;
        Color = color;
        MakeTech = makeTech;
        MakeTechName = makeTechName;
        Maintenance = maintenance;
        MaintenenceName = maintenenceName;
        Relics = relics;
        Vendor = vendor;
        Cost = cost;
        VendorAddress = vendorAddress;
        Content = content;
        Field = field;
        FieldName = fieldName;
        Collector = collector;
        CollectorName = collectorName;
        CollectorDate = collectorDate;
        Image = image;
        ExhibitSee = exhibitSee;
        ExhibitHeart = exhibitHeart;
    }


    public int getExhID() {
        return ExhID;
    }

    public void setExhID(int exhID) {
        ExhID = exhID;
    }

    public String getExhibitName() {
        return ExhibitName;
    }

    public void setExhibitName(String exhibitName) {
        ExhibitName = exhibitName;
    }

    public String getOtherName() {
        return OtherName;
    }

    public void setOtherName(String otherName) {
        OtherName = otherName;
    }

    public String getCodeID() {
        return CodeID;
    }

    public void setCodeID(String codeID) {
        CodeID = codeID;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public String getElement() {
        return Element;
    }

    public void setElement(String element) {
        Element = element;
    }

    public int getMaterialType() {
        return MaterialType;
    }

    public void setMaterialType(int materialType) {
        MaterialType = materialType;
    }

    public String getMaterialName() {
        return MaterialName;
    }

    public void setMaterialName(String materialName) {
        MaterialName = materialName;
    }

    public Date getVerificationDate() {
        return VerificationDate;
    }

    public void setVerificationDate(Date verificationDate) {
        VerificationDate = verificationDate;
    }

    public String getAges() {
        return Ages;
    }

    public void setAges(String ages) {
        Ages = ages;
    }

    public int getExpertise() {
        return Expertise;
    }

    public void setExpertise(int expertise) {
        Expertise = expertise;
    }

    public String getExpertiseName() {
        return ExpertiseName;
    }

    public void setExpertiseName(String expertiseName) {
        ExpertiseName = expertiseName;
    }

    public Date getExpertiseDate() {
        return ExpertiseDate;
    }

    public void setExpertiseDate(Date expertiseDate) {
        ExpertiseDate = expertiseDate;
    }

    public int getSource() {
        return Source;
    }

    public void setSource(int source) {
        Source = source;
    }

    public String getSourceName() {
        return SourceName;
    }

    public void setSourceName(String sourceName) {
        SourceName = sourceName;
    }

    public String getResourceNote() {
        return ResourceNote;
    }

    public void setResourceNote(String resourceNote) {
        ResourceNote = resourceNote;
    }

    public int getTime() {
        return Time;
    }

    public void setTime(int time) {
        Time = time;
    }

    public String getTimeName() {
        return TimeName;
    }

    public void setTimeName(String timeName) {
        TimeName = timeName;
    }

    public String getTimeNote() {
        return TimeNote;
    }

    public void setTimeNote(String timeNote) {
        TimeNote = timeNote;
    }

    public String getLocationID() {
        return LocationID;
    }

    public void setLocationID(String locationID) {
        LocationID = locationID;
    }

    public String getLacationNote() {
        return LacationNote;
    }

    public void setLacationNote(String lacationNote) {
        LacationNote = lacationNote;
    }

    public int getOwner() {
        return Owner;
    }

    public void setOwner(int owner) {
        Owner = owner;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerAddress() {
        return OwnerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        OwnerAddress = ownerAddress;
    }

    public int getSecretLevel() {
        return SecretLevel;
    }

    public void setSecretLevel(int secretLevel) {
        SecretLevel = secretLevel;
    }

    public String getSecretLevelName() {
        return SecretLevelName;
    }

    public void setSecretLevelName(String secretLevelName) {
        SecretLevelName = secretLevelName;
    }

    public int getStuff() {
        return Stuff;
    }

    public void setStuff(int stuff) {
        Stuff = stuff;
    }

    public String getStuffName() {
        return StuffName;
    }

    public void setStuffName(String stuffName) {
        StuffName = stuffName;
    }

    public String getDescPhysics() {
        return DescPhysics;
    }

    public void setDescPhysics(String descPhysics) {
        DescPhysics = descPhysics;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public int getMakeTech() {
        return MakeTech;
    }

    public void setMakeTech(int makeTech) {
        MakeTech = makeTech;
    }

    public String getMakeTechName() {
        return MakeTechName;
    }

    public void setMakeTechName(String makeTechName) {
        MakeTechName = makeTechName;
    }

    public int getMaintenance() {
        return Maintenance;
    }

    public void setMaintenance(int maintenance) {
        Maintenance = maintenance;
    }

    public String getMaintenenceName() {
        return MaintenenceName;
    }

    public void setMaintenenceName(String maintenenceName) {
        MaintenenceName = maintenenceName;
    }

    public String getRelics() {
        return Relics;
    }

    public void setRelics(String relics) {
        Relics = relics;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public int getCost() {
        return Cost;
    }

    public void setCost(int cost) {
        Cost = cost;
    }

    public String getVendorAddress() {
        return VendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        VendorAddress = vendorAddress;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getField() {
        return Field;
    }

    public void setField(int field) {
        Field = field;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }

    public int getCollector() {
        return Collector;
    }

    public void setCollector(int collector) {
        Collector = collector;
    }

    public String getCollectorName() {
        return CollectorName;
    }

    public void setCollectorName(String collectorName) {
        CollectorName = collectorName;
    }

    public Date getCollectorDate() {
        return CollectorDate;
    }

    public void setCollectorDate(Date collectorDate) {
        CollectorDate = collectorDate;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getExhibitSee() {
        return ExhibitSee;
    }

    public void setExhibitSee(String exhibitSee) {
        ExhibitSee = exhibitSee;
    }

    public String getExhibitHeart() {
        return ExhibitHeart;
    }

    public void setExhibitHeart(String exhibitHeart) {
        ExhibitHeart = exhibitHeart;
    }
}
