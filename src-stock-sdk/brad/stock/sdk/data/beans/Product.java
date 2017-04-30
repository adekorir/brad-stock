package brad.stock.sdk.data.beans;

import brad.util.data.bean.Category;
import brad.util.data.bean.IDBean;
import brad.util.data.bean.SubCategory;
import javafx.beans.property.*;

public class Product extends IDBean {

    private static final long serailVersionUID = 123L;

    private SubCategory _subCategory;
    private ObjectProperty<SubCategory> subCategory;

    private String _description, _notes;
    private StringProperty description, notes;

    private double _salePrice, _purchasePrice, _reorderLimit, _reorderAmount;
    private DoubleProperty salePrice, purchasePrice, reorderLimit, reorderAmount;

    private boolean _discontinued;
    private BooleanProperty discontinued;

    {
        _subCategory = null;

        _description = "";

        _salePrice = _purchasePrice = _reorderLimit = _reorderAmount = 0.0;

        _discontinued = true;
    }

    public Product() {
        super();
    }

    public Category getSubCategory() {
        if (subCategory == null)
            return _subCategory;
        else
            return subCategory.get();
    }

    public ObjectProperty<SubCategory> subCategoryProperty() {
        if (subCategory == null)
            subCategory = new SimpleObjectProperty<>(this, "subCategory", _subCategory);
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        if (this.subCategory == null)
            _subCategory = subCategory;
        else
            this.subCategory.set(subCategory);
    }

    public String getDescription() {
        if (description == null)
            return _description;
        else
            return description.get();
    }

    public StringProperty descriptionProperty() {
        if (description == null)
            description = new SimpleStringProperty(this, "description", _description);
        return description;
    }

    public void setDescription(String description) {
        if (this.description == null)
            _description = description;
        else
            this.description.set(description);
    }

    public String getNotes() {
        if (notes == null)
            return _notes;
        else
            return notes.get();
    }

    public StringProperty notesProperty() {
        if (notes == null)
            notes = new SimpleStringProperty(this, "notes", _notes);
        return notes;
    }

    public void setNotes(String notes) {
        if (this.notes == null)
            _notes = notes;
        else
            this.notes.set(notes);
    }

    public double getSalePrice() {
        if (salePrice == null)
            return _salePrice;
        else
            return salePrice.get();
    }

    public DoubleProperty salePriceProperty() {
        if (salePrice == null)
            salePrice = new SimpleDoubleProperty(this, "salePrice", _salePrice);
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        if (this.salePrice == null)
            _salePrice = salePrice;
        else
            this.salePrice.set(salePrice);
    }

    public double getPurchasePrice() {
        if (purchasePrice == null)
            return _purchasePrice;
        else
            return purchasePrice.get();
    }

    public DoubleProperty purchasePriceProperty() {
        if (purchasePrice == null)
            purchasePrice = new SimpleDoubleProperty(this, "purchasePrice", _purchasePrice);
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        if (this.purchasePrice == null)
            _purchasePrice = purchasePrice;
        else
            this.purchasePrice.set(purchasePrice);
    }

    public double getReorderLimit() {
        if (reorderLimit == null)
            return _reorderLimit;
        else
            return reorderLimit.get();
    }

    public DoubleProperty reorderLimitProperty() {
        if (reorderLimit == null)
            reorderLimit = new SimpleDoubleProperty(this, "reorderLimit", _reorderLimit);
        return reorderLimit;
    }

    public void setReorderLimit(double reorderLimit) {
        if (this.reorderLimit == null)
            _reorderLimit = reorderLimit;
        else
            this.reorderLimit.set(reorderLimit);
    }

    public double getReorderAmount() {
        if (reorderAmount == null)
            return _reorderAmount;
        else
            return reorderAmount.get();
    }

    public DoubleProperty reorderAmountProperty() {
        if (reorderAmount == null)
            reorderAmount = new SimpleDoubleProperty(this, "reorderAmount", _reorderAmount);
        return reorderAmount;
    }

    public void setReorderAmount(double reorderAmount) {
        if (this.reorderAmount == null)
            _reorderAmount = reorderAmount;
        else
            this.reorderAmount.set(reorderAmount);
    }

    public boolean isDiscontinued() {
        if (discontinued == null)
            return _discontinued;
        else
            return discontinued.get();
    }

    public BooleanProperty discontinuedProperty() {
        if (discontinued == null)
            discontinued = new SimpleBooleanProperty(this, "discontinued", _discontinued);
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        if (this.discontinued == null)
            _discontinued = discontinued;
        else
            this.discontinued.set(discontinued);
    }

    @Override
    public String toString() {
        return getDescription();
    }
}
