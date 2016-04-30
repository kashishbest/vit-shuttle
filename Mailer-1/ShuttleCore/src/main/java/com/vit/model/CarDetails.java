package com.vit.model;

/**
 * Created by kashishsinghal on 11/04/16.
 */
public class CarDetails {
    int price;
    String colors;
    String desc;
    String image;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarDetails that = (CarDetails) o;

        if (price != that.price) return false;
        if (colors != null ? !colors.equals(that.colors) : that.colors != null) return false;
        if (desc != null ? !desc.equals(that.desc) : that.desc != null) return false;
        return !(image != null ? !image.equals(that.image) : that.image != null);

    }

    @Override
    public int hashCode() {
        int result = price;
        result = 31 * result + (colors != null ? colors.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarDetails{" +
                "price=" + price +
                ", colors='" + colors + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
