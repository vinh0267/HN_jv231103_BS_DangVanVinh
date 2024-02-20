package ra.model.sevice;

import ra.model.model.Catalog;

import java.util.ArrayList;
import java.util.List;

public class CatalogService implements IGenericService<Catalog, Integer> {
    private static List<Catalog> catalogs;

    public CatalogService() {
        this.catalogs = new ArrayList<>();
    }


    @Override
    public List<Catalog> getAll() {
        return catalogs;
    }

    @Override
    public void save(Catalog catalog) {
        catalogs.add(catalog);
    }

    @Override
    public Catalog findById(Integer catalogId) {
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == catalogId) {
                return catalog;
            }
        }
        return null;
    }

    @Override
    public void delete(Integer catalogId) {
        Catalog catalogToRemove = null;
        for (Catalog catalog : catalogs) {
            if (catalog.getCatalogId() == catalogId) {
                catalogToRemove = catalog;
                break;
            }
        }
        if (catalogToRemove != null) {
            catalogs.remove(catalogToRemove);
        }
    }

    @Override
    public boolean hasProductsInCatalog(Catalog catalogToDelete) {
        return false;
    }

    @Override
    public Object findById() {
        return null;
    }
}
