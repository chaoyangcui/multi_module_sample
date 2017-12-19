package com.sssarm.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by cuiguiyang on 2017/2/7 22:29.
 * Desc KF信息
 */
@Document(collection = "room_user_info")
public class RoomUserInfo {

    @Field("name")
    private String Name;
    @Field("name")
    private String CardNo;
    @Field("name")
    private String Descriot;
    @Field("name")
    private String CtfTp;
    @Field("name")
    private String CtfId;
    @Field("name")
    private String Gender;
    @Field("name")
    private String Birthday;
    @Field("name")
    private String Address;
    @Field("name")
    private String Zip;
    @Field("name")
    private String Dirty;
    @Field("name")
    private String District1;
    @Field("name")
    private String District2;
    @Field("name")
    private String District3;
    @Field("name")
    private String District4;
    @Field("name")
    private String District5;
    @Field("name")
    private String District6;
    @Field("name")
    private String FirstNm;
    @Field("name")
    private String LastNm;
    @Field("name")
    private String Duty;
    @Field("name")
    private String Mobile;
    @Field("name")
    private String Tel;
    @Field("name")
    private String Fax;
    @Field("name")
    private String EMail;
    @Field("name")
    private String Nation;
    @Field("name")
    private String Taste;
    @Field("name")
    private String Education;
    @Field("name")
    private String Company;
    @Field("name")
    private String CTel;
    @Field("name")
    private String CAddress;
    @Field("name")
    private String CZip;
    @Field("name")
    private String Family;
    @Field("name")
    private String Version;
    @Field("name")
    private String id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCardNo() {
        return CardNo;
    }

    public void setCardNo(String cardNo) {
        CardNo = cardNo;
    }

    public String getDescriot() {
        return Descriot;
    }

    public void setDescriot(String descriot) {
        Descriot = descriot;
    }

    public String getCtfTp() {
        return CtfTp;
    }

    public void setCtfTp(String ctfTp) {
        CtfTp = ctfTp;
    }

    public String getCtfId() {
        return CtfId;
    }

    public void setCtfId(String ctfId) {
        CtfId = ctfId;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getZip() {
        return Zip;
    }

    public void setZip(String zip) {
        Zip = zip;
    }

    public String getDirty() {
        return Dirty;
    }

    public void setDirty(String dirty) {
        Dirty = dirty;
    }

    public String getDistrict1() {
        return District1;
    }

    public void setDistrict1(String district1) {
        District1 = district1;
    }

    public String getDistrict2() {
        return District2;
    }

    public void setDistrict2(String district2) {
        District2 = district2;
    }

    public String getDistrict3() {
        return District3;
    }

    public void setDistrict3(String district3) {
        District3 = district3;
    }

    public String getDistrict4() {
        return District4;
    }

    public void setDistrict4(String district4) {
        District4 = district4;
    }

    public String getDistrict5() {
        return District5;
    }

    public void setDistrict5(String district5) {
        District5 = district5;
    }

    public String getDistrict6() {
        return District6;
    }

    public void setDistrict6(String district6) {
        District6 = district6;
    }

    public String getFirstNm() {
        return FirstNm;
    }

    public void setFirstNm(String firstNm) {
        FirstNm = firstNm;
    }

    public String getLastNm() {
        return LastNm;
    }

    public void setLastNm(String lastNm) {
        LastNm = lastNm;
    }

    public String getDuty() {
        return Duty;
    }

    public void setDuty(String duty) {
        Duty = duty;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEMail() {
        return EMail;
    }

    public void setEMail(String EMail) {
        this.EMail = EMail;
    }

    public String getNation() {
        return Nation;
    }

    public void setNation(String nation) {
        Nation = nation;
    }

    public String getTaste() {
        return Taste;
    }

    public void setTaste(String taste) {
        Taste = taste;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getCTel() {
        return CTel;
    }

    public void setCTel(String CTel) {
        this.CTel = CTel;
    }

    public String getCAddress() {
        return CAddress;
    }

    public void setCAddress(String CAddress) {
        this.CAddress = CAddress;
    }

    public String getCZip() {
        return CZip;
    }

    public void setCZip(String CZip) {
        this.CZip = CZip;
    }

    public String getFamily() {
        return Family;
    }

    public void setFamily(String family) {
        Family = family;
    }

    public String getVersion() {
        return Version;
    }

    public void setVersion(String version) {
        Version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomUserInfo that = (RoomUserInfo) o;

        if (!Name.equals(that.Name)) return false;
        if (!CardNo.equals(that.CardNo)) return false;
        if (!Descriot.equals(that.Descriot)) return false;
        if (!CtfTp.equals(that.CtfTp)) return false;
        if (!CtfId.equals(that.CtfId)) return false;
        if (!Gender.equals(that.Gender)) return false;
        if (!Birthday.equals(that.Birthday)) return false;
        if (!Address.equals(that.Address)) return false;
        if (!Zip.equals(that.Zip)) return false;
        if (!Dirty.equals(that.Dirty)) return false;
        if (!District1.equals(that.District1)) return false;
        if (!District2.equals(that.District2)) return false;
        if (!District3.equals(that.District3)) return false;
        if (!District4.equals(that.District4)) return false;
        if (!District5.equals(that.District5)) return false;
        if (!District6.equals(that.District6)) return false;
        if (!FirstNm.equals(that.FirstNm)) return false;
        if (!LastNm.equals(that.LastNm)) return false;
        if (!Duty.equals(that.Duty)) return false;
        if (!Mobile.equals(that.Mobile)) return false;
        if (!Tel.equals(that.Tel)) return false;
        if (!Fax.equals(that.Fax)) return false;
        if (!EMail.equals(that.EMail)) return false;
        if (!Nation.equals(that.Nation)) return false;
        if (!Taste.equals(that.Taste)) return false;
        if (!Education.equals(that.Education)) return false;
        if (!Company.equals(that.Company)) return false;
        if (!CTel.equals(that.CTel)) return false;
        if (!CAddress.equals(that.CAddress)) return false;
        if (!CZip.equals(that.CZip)) return false;
        if (!Family.equals(that.Family)) return false;
        if (!Version.equals(that.Version)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        int result = Name.hashCode();
        result = 31 * result + CardNo.hashCode();
        result = 31 * result + Descriot.hashCode();
        result = 31 * result + CtfTp.hashCode();
        result = 31 * result + CtfId.hashCode();
        result = 31 * result + Gender.hashCode();
        result = 31 * result + Birthday.hashCode();
        result = 31 * result + Address.hashCode();
        result = 31 * result + Zip.hashCode();
        result = 31 * result + Dirty.hashCode();
        result = 31 * result + District1.hashCode();
        result = 31 * result + District2.hashCode();
        result = 31 * result + District3.hashCode();
        result = 31 * result + District4.hashCode();
        result = 31 * result + District5.hashCode();
        result = 31 * result + District6.hashCode();
        result = 31 * result + FirstNm.hashCode();
        result = 31 * result + LastNm.hashCode();
        result = 31 * result + Duty.hashCode();
        result = 31 * result + Mobile.hashCode();
        result = 31 * result + Tel.hashCode();
        result = 31 * result + Fax.hashCode();
        result = 31 * result + EMail.hashCode();
        result = 31 * result + Nation.hashCode();
        result = 31 * result + Taste.hashCode();
        result = 31 * result + Education.hashCode();
        result = 31 * result + Company.hashCode();
        result = 31 * result + CTel.hashCode();
        result = 31 * result + CAddress.hashCode();
        result = 31 * result + CZip.hashCode();
        result = 31 * result + Family.hashCode();
        result = 31 * result + Version.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }
}
