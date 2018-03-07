package com.github.luecy1.basicsample.db.dao;

import com.github.luecy1.basicsample.db.entry.CommentEntry;
import com.github.luecy1.basicsample.db.entry.ProductEntry;
import com.github.luecy1.basicsample.model.Product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by you on 2018/03/07.
 */
public class DataGenerator {

    private static final String[] FIRST = new String[]{
            "Special edition", "New", "Cheep", "Quality", "Used"
    };

    private static final String[] SECOND = new String[]{
            "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle"
    };

    private static final String[] DESCRIPTION = new String[]{
            "is finally here", "is recommended by Stan S Stanman",
            "is the best sold product on Melee Island", "is \uD83D\uDCAF", "is 💜", "is fine"
    };

    private static final String[] COMMENTS = new String[]{
            "Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment6"
    };

    public static List<ProductEntry> generateProducts() {
        List<ProductEntry> products = new ArrayList<>(FIRST.length * SECOND.length);
        Random rnd = new Random();

        for (int i = 0; i < FIRST.length; i++) {
            for (int j = 0; j < SECOND.length; j++) {
                ProductEntry product = new ProductEntry();
                product.setName(FIRST[i] + " " + SECOND[j]);
                product.setDescription(product.getName() + " " + DESCRIPTION[j]);
                product.setPrice(rnd.nextInt(240));
                product.setId(FIRST.length * i + j + 1);
                products.add(product);
            }
        }
        return products;
    }

    public static List<CommentEntry> generateCommentsForProducts(
            final List<ProductEntry> products) {
        List<CommentEntry> comments = new ArrayList<>();
        Random rnd = new Random();


        for (Product product : products) {
            int commentsNumber = rnd.nextInt(5) + 1;

            for (int i = 0; i < commentsNumber; i++) {
                CommentEntry comment = new CommentEntry();
                comment.setProductId(product.getId());
                comment.setText(COMMENTS[i] + " for " + product.getName());
                comment.setPostedAt(new Date(System.currentTimeMillis()
                        - TimeUnit.DAYS.toMillis(commentsNumber - i) + TimeUnit.HOURS.toMillis(i)));
                comments.add(comment);
            }
        }

        return comments;
    }
}
