package gd.dto;

import atg.commerce.order.CommerceItem;
import gd.utils.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class ItemDto {

    private String skuId;

    private String productId;

    private String itemType;

    private Long quantity;

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public static ItemBuilder newBuilder() {
        return new ItemDto().new ItemBuilder();
    }

    public class ItemBuilder {

        public ItemBuilder setSkuId(String skuId) {
            ItemDto.this.skuId = skuId;
            return this;
        }

        public ItemBuilder setQuantity(Long quantity) {
            ItemDto.this.quantity = quantity;
            return this;
        }

        public ItemBuilder setProductId(String productId) {
            ItemDto.this.productId = productId;
            return this;
        }

        public ItemBuilder setItemType(String itemType) {
            ItemDto.this.itemType = itemType;
            return this;
        }

        public ItemDto build() {
            return ItemDto.this;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.writeObject(this);
    }

    public static List<ItemDto> listFromCommerceItems(List<CommerceItem> commerceItems) {
        List<ItemDto> result = new ArrayList<ItemDto>();

        for (CommerceItem commerceItem : commerceItems) {
            result.add(fromCommerceItem(commerceItem));
        }

        return result;
    }

    public static ItemDto fromCommerceItem(CommerceItem commerceItem) {
        return newBuilder()
                .setSkuId(commerceItem.getCatalogRefId())
                .setQuantity(commerceItem.getQuantity())
                .setItemType(commerceItem.getCommerceItemClassType())
                .setProductId(commerceItem.getAuxiliaryData().getProductId())
                .build();
    }

}
