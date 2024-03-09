Select t1.product_name
From
    Order t1
        Inner Join Customer t2 On t2.id = t1.id And t2.name = :name