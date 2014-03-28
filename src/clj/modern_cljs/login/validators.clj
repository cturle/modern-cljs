(ns modern-cljs.login.validators
  (:require [valip.predicates :refer [email-address?]]))

(def ^:dynamic *password-re* #"^(?=.*\d).{4,8}$")
;(def ^:dynamic *email-re* #"^[_a-z0-9-]+(\.[_a-z0-9-]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$")

(defn isa-email? [v]
  (email-address? v) )

(defn isa-password? [v]
  (boolean (re-matches *password-re* v)) )

