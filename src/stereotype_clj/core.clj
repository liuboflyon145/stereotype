(ns stereotype-clj.core)

(defmacro defsterotype
  "define a sterotype with default attributes"
  [name attributes] attributes)

(defn sterotype
  "returns the sterotype defaults"
  [name & [overiding_attributes]]
  (merge {:name "josephwilk" :company "soundcloud"} overiding_attributes))