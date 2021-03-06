package be.intecbrussel.sellers;

import be.intecbrussel.eatables.*;

public class IceCreamCar implements IceCreamSeller {
    private PriceList priceList;
    private Stock stock;
    private static double profit;

    public IceCreamCar(PriceList pricelist, Stock stock) {
        this.priceList = pricelist;
        this.stock = stock;
        profit = 0;
    }

    @Override
    public Cone orderCone(Flavor[] flavor) {
        try {
            if (stock.getBalls() < flavor.length) {
                throw new NoMoreIceCreamException("No more balls");
            }
        } catch (NoMoreIceCreamException noMoreIceCreamException) {
            System.out.println(noMoreIceCreamException.toString());
        }

        try {
            if (stock.getCones() < 1) {
                throw new NoMoreIceCreamException("No more cones");
            }
        } catch (NoMoreIceCreamException noMoreIceCreamException) {
            System.out.println(noMoreIceCreamException.toString());
        }

        stock.setCones(stock.getCones() - 1);
        stock.setBalls(stock.getBalls() - flavor.length);
        profit += priceList.getBallPrice(flavor);
        return prepareCone(flavor);
    }

    private Cone prepareCone(Flavor[] flavor) {
        return new Cone(flavor);
    }

    @Override
    public IceRocket orderIceRocket() {
        try {
            if (stock.getIceRockets() < 1) {
                throw new NoMoreIceCreamException("No more icerockets");
            }
        } catch (NoMoreIceCreamException noMoreIceCreamException) {
            System.out.println(noMoreIceCreamException.toString());
        }
        stock.setIceRockets(stock.getIceRockets() - 1);
        profit += priceList.getRocketPrice();
        return prepareIceRocket();
    }

    private IceRocket prepareIceRocket() {
        return new IceRocket();
    }

    @Override
    public Magnum orderMagnum(MagnumType magnumType) {
        try {
            if (stock.getMagni() < 1) {
                throw new NoMoreIceCreamException("No more magni");
            }
        } catch (NoMoreIceCreamException noMoreIceCreamException) {
            System.out.println(noMoreIceCreamException.toString());
        }
        stock.setMagni(stock.getMagni() - 1);
        profit += priceList.getMagnumPrice(magnumType);
        return prepareMagnum(magnumType);
    }

    private Magnum prepareMagnum(MagnumType magnum) {
        return new Magnum(magnum);
    }

    @Override
    public double getProfit() {
        return profit;
    }
}
