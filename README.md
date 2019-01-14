Start the app with `sbt run` and call it a few times with `curl localhost:9000/test`

After a while you should see the traceIds missing or reused in the logs. Something like:
```
2019-01-14 14:54:33,827 [undefined] INFO  [Main$] [scala-execution-context-global-110] - Log test
2019-01-14 14:54:34,510 [ff1a3ad17596a3df] INFO  [Main$] [scala-execution-context-global-123] - Log test
2019-01-14 14:54:35,105 [undefined] INFO  [Main$] [scala-execution-context-global-123] - Log test
2019-01-14 14:54:35,657 [90471d1adebe4dc0] INFO  [Main$] [scala-execution-context-global-110] - Log test
2019-01-14 14:54:36,252 [90471d1adebe4dc0] INFO  [Main$] [scala-execution-context-global-110] - Log test
2019-01-14 14:54:36,816 [90471d1adebe4dc0] INFO  [Main$] [scala-execution-context-global-123] - Log test
2019-01-14 14:54:37,368 [90471d1adebe4dc0] INFO  [Main$] [scala-execution-context-global-110] - Log test
```

