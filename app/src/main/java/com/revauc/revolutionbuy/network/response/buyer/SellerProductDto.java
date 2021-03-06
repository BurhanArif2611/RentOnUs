package com.revauc.revolutionbuy.network.response.buyer;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.revauc.revolutionbuy.network.response.UserDto;

import java.util.List;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerProductDto implements Parcelable {


    private int id;
    private int userId;
    private int buyerProductId;
    private int price;
    private String description;
    private String sellerProductTypeText;
    private UserDto user;
    private List<BuyerProductImageDto> sellerProductImages;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getBuyerProductId() {
        return buyerProductId;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getSellerProductTypeText() {
        return sellerProductTypeText;
    }

    public UserDto getUser() {
        return user;
    }

    public List<BuyerProductImageDto> getSellerProductImages() {
        return sellerProductImages;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.userId);
        dest.writeInt(this.buyerProductId);
        dest.writeInt(this.price);
        dest.writeString(this.description);
        dest.writeString(this.sellerProductTypeText);
        dest.writeParcelable(this.user, flags);
        dest.writeTypedList(this.sellerProductImages);
    }

    public SellerProductDto() {
    }

    protected SellerProductDto(Parcel in) {
        this.id = in.readInt();
        this.userId = in.readInt();
        this.buyerProductId = in.readInt();
        this.price = in.readInt();
        this.description = in.readString();
        this.sellerProductTypeText = in.readString();
        this.user = in.readParcelable(UserDto.class.getClassLoader());
        this.sellerProductImages = in.createTypedArrayList(BuyerProductImageDto.CREATOR);
    }

    public static final Creator<SellerProductDto> CREATOR = new Creator<SellerProductDto>() {
        @Override
        public SellerProductDto createFromParcel(Parcel source) {
            return new SellerProductDto(source);
        }

        @Override
        public SellerProductDto[] newArray(int size) {
            return new SellerProductDto[size];
        }
    };
}
