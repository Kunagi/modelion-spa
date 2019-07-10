(ns modelion-spa.navigator
  (:require
   [facts-db.api :as db]))


(def model-id "model")


(defn new-navigator
  [model-db]
  {:entity (db/tree model-db model-id {:model/modules {}})
   :view :model})



(defn entity-for-view
  [model-db entity-id view]
  (case view

    :model (db/tree model-db entity-id {:model/modules {}})

    :module (db/tree model-db entity-id {:module/resources {}
                                         :module/ajax-resource-loaders {}
                                         :module/ui-components {}})

    nil))

(defn activate-view
  [navigator model-db view entity-id]
  (-> navigator
      (assoc :view view)
      (assoc :entity (entity-for-view model-db entity-id view))))
