db.imdb.find(
    {
        $or: [
            {
                $and: [
                    {runtime: 199},
                    {year: 1914}
                ]
            },
            {year: {$lte: 1914}}
        ]
    },
    {
        plot: 1, genres: 1
    }).limit(20)

db.imdb.find({ $and: [{ "genres": { $size: 2 } }, { "genres": { $all: ["Action", "Comedy"] } }] }, { awards: 1, title: 1, genres: 1 }).limit(20)




    .explain("executionStats")

db.car.aggregate(
    [
        {
            $lookup: {
                from: "sell",
                localField: "_id",
                foreignField: "car_id",
                as: "orders"
            }
        },
        {
            $limit: 40
        }
    ]
)

db.car.aggregate(
    [
    {
        "$project": {
            "firstName": 1,
            "lastName": 1,
            "height": 1,
            "weight": 1
        }
    },
        {
            "$match": {
                "weight": { "$gte": 100 }
            }
        }
    ]
)