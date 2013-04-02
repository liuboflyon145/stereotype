(ns stereotype.stereotypes
  (:use
    [korma.db]
    [korma.core]
    [slingshot.slingshot    :only [throw+]])
  (:require
   [stereotype.entities    :as entities]
   [stereotype.sql         :as sql]
   [stereotype.resolve-map :as resolve-map]))

(defn fn-name [identifier]
  (let [stereotype-id (entities/id-for identifier)]
    (->> stereotype-id
         name
         (str "stereotype-")
         symbol)))

(defn stereotype-fn [attributes]
  (fn [& [overiding-attributes]]
    (merge attributes overiding-attributes)))

(defn define [stereotype-id attributes]
  `(defn ~(fn-name stereotype-id) [overiding-attributes#]
     (apply (stereotype-fn ~attributes)
            [overiding-attributes#])))

(defn- attributes-for [stereotype-id overiding-attributes]
  (let [matching-stereotype-fn (resolve (fn-name stereotype-id))]
    (when-not matching-stereotype-fn
      (throw+ {:type ::undefined-stereotype
               :stereotype (entities/id-for stereotype-id)}))
    (matching-stereotype-fn overiding-attributes)))

(defn- map-insertions-to-keys [attributes]
  (into {}
    (map sql/replace-inserts-as-foreign-keys attributes)))

(defn- merge-with-meta [map-1 map-2]
  (let [meta-data (merge (meta map-1) (meta map-2))]
    (with-meta (merge map-1 map-2) meta-data)))

(defn build [identifier & [overiding-attributes]]
  (let [attributes (attributes-for identifier overiding-attributes)]
    (resolve-map/all attributes)))

(defn build-and-insert [identifier & [overiding-attributes]]
  (let [attributes (->> overiding-attributes (build identifier) map-insertions-to-keys)
        insertion-result (insert (entities/entity-for identifier) (values attributes))]
    (merge-with-meta attributes (sql/pk insertion-result))))