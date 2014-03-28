(ns modern-cljs.login
  (:require-macros [hiccups.core :refer [html]])
  (:require [domina :as dom]
            [domina.events :as ev]
            [hiccups.runtime :as hiccupsrt]
            [shoreleave.remotes.http-rpc :refer [remote-callback]]
            [modern-cljs.login.validators :refer [isa-email? isa-password?]]
            ))

(def version-cljs "13.1")

(defn validate-email [email]
  (aset (dom/by-id "email-alert-msg") "hidden" (isa-email? (aget email "value"))) )

(defn validate-password [password]
  (aset (dom/by-id "password-alert-msg") "hidden" (isa-password? (aget password "value"))) )

(defn validate-form [e]
  (when (some false? [(validate-email    (dom/by-id "email"))
                      (validate-password (dom/by-id "password"))
                    ])
    (ev/prevent-default e) ))

(defn ^:export init []
  (when (and js/document
             (aget js/document "getElementById"))
    (ev/listen! (dom/by-id "submit") :click validate-form)
    (let [email    (dom/by-id "email")
          password (dom/by-id "password")]
      (ev/listen! email    :blur (fn [evt] (validate-email email)))
      (ev/listen! password :blur (fn [evt] (validate-password password)))
      )
    ;; version infos
    (dom/set-text! (dom/by-id "cljs-version") (str "version CLJS " version-cljs))
    (remote-callback :clj-version []
                     #(dom/set-text! (dom/by-id "clj-version") (str "version CLJ " %)) )))


