(ns stereotype-clj.core
  (:require
    [korma.db   :refer :all]
    [korma.core :refer :all]))

(def sterotypes (atom {}))

(defn update-sterotypes [new-sterotype]
  (reset! sterotypes (merge @sterotypes new-sterotype)))

(defmacro defsterotype
  "define a sterotype with default attributes"
  [name attributes]
  (update-sterotypes {name attributes})
  (println @sterotypes))

(defn sterotype
  "returns the sterotype defaults"
  [name & [overiding_attributes]]
  (merge (name @sterotypes) overiding_attributes))

(defn sterotype!
  "returns the sterotype and creates it in the db"
  [name & [overiding_attributes]]
  (insert name
    (values (sterotype name overiding_attributes))))