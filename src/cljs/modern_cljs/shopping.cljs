(ns modern-cljs.shopping
  (:require-macros [hiccups.core :as h])
  (:require [domina :as dom]
            [domina.events :as ev]
            [hiccups.runtime :as hiccupsrt]
            [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [cljs.reader :refer [read-string]] ))            

(defn calculate []
  (let [quantity (read-string (dom/value (dom/by-id "quantity")))
        price    (read-string (dom/value (dom/by-id "price")))
        tax      (read-string (dom/value (dom/by-id "tax")))
        discount (read-string (dom/value (dom/by-id "discount"))) ]
    (remote-callback :calculate [quantity price tax discount]
                     #(dom/set-value! (dom/by-id "total") (.toFixed % 2)) )))

(defn add-help []
  (dom/append! (dom/by-id "shoppingForm")
               (h/html [:div {:class "help"} "Click to calculate"]) ))  

               
(defn remove-help []
  (dom/destroy! (dom/by-class "help")) )
               

(defn ^:export init []
  (when (and js/document
           (aget js/document "getElementById"))
    (remote-callback :title-version []
                     #(dom/set-text! (dom/by-id "titre") (str "Shopping Calculator " %)) ) 
    (ev/listen! (dom/by-id "calc") :click calculate)
    (ev/listen! (dom/by-id "calc") :mouseover add-help)
    (ev/listen! (dom/by-id "calc") :mouseout remove-help) ))

