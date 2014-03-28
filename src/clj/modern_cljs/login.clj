(ns modern-cljs.login
  (:require [modern-cljs.login.validators :refer [isa-email? isa-password?]]))


(defn authenticate-user [email password]
  (if (or (empty? email) (empty? password))
    (str "Please complete the form")
    (if (and (isa-email? email)
             (isa-password? password))
      (str email " and " password
           " passed the formal validation, but you still have to be authenticated")
      (str email " or " password " are bad :("))))

