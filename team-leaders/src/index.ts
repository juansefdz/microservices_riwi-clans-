import express, { Request, Response } from "express";
import { AppDataSource } from "./Config/connection-db";
import { error, log } from "console";
import teamLeaderRouter from "./router/team-leader.route";
import { client } from "./Config/eureka-client";

const app = express();
app.use(express.json());

app.get("/", (req: Request, res: Response) => {
  res.json({ message: "Hello World!" });
});

app.use("/api/v1/team-leader", teamLeaderRouter);

AppDataSource.initialize()
  .then(() => {
    console.log("Database connection established successfully");
    client.start((error) => {
      log(error || "Eureka client started");
    });

    app.listen(7000, () => {
      console.log("Server is running on port 7000");
    });
  })
  .catch((error) => {
    console.log("Error connecting to the database: ", error);
  });
