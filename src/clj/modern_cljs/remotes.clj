(ns modern-cljs.remotes
  (:require [modern-cljs.core :refer [handler]]
            [compojure.handler :refer [site]]
            [shoreleave.middleware.rpc :refer [defremote wrap-rpc]]))

(defremote calculate [quantity price tax discount]
  (-> (* quantity price)
      (* (+ 1 (/ tax 100)))
      (- discount)))

(defremote clj-version []
  "13.1" )

(def app (-> (var handler)
             (wrap-rpc)
             (site)))

