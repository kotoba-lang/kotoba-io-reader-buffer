(ns kotoba.io.reader-buffer
  "reader-buffer -- addressed on its own.

  Split out of kotoba.lang.io on 2026-09-09 (ADR-2609091200). The unit
  here is the DEFINITION, and this repo's deps.edn names exactly the
  definitions it reaches -- nothing else.
"
  (:require [kotoba.io.ireader :refer [IReader read!]]
            [kotoba.io.to-bytes :refer [to-bytes]])
)

(defn reader-buffer
  "Adapt `buf` as a one-shot IReader: a single read returns the whole buffer,
  then EOF."
  [buf]
  (let [done (atom false)]
    (reify IReader
      (read! [_]
        (if @done nil (do (reset! done true) (to-bytes buf)))))))
