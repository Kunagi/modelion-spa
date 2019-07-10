(ns modelion-spa.navigator
  (:require
   [facts-db.api :as db]))


(def model-id "model")


(def model-breadcrumb
 {:text "Model"
  :goto-event [:modelion/activate-view
               {:view :model
                :entity-id "model"}]})


(defn new-navigator
  [model-db]
  {:entity (db/tree model-db model-id {:model/modules {}})
   :view :model
   :breadcrumbs [model-breadcrumb]})


(defn entity-for-view
  [model-db entity-id view]
  (case view

    :model (db/tree model-db entity-id {:model/modules {}})

    :module (db/tree model-db entity-id {:module/resources {}
                                         :module/ajax-resource-loaders {}
                                         :module/ui-components {}})

    nil))


(defn active-breadcrumb
  [model-db view entity-id]
  (let [entity (get model-db entity-id)]
    {:text (-> entity :modeling/name)}))


(defn breadcrumbs
  [model-db view entity-id]
  (if (= :model view)
    [model-breadcrumb]
    [model-breadcrumb
     (active-breadcrumb model-db view entity-id)]))


(defn activate-view
  [navigator model-db view entity-id]
  (-> navigator
      (assoc :view view)
      (assoc :entity (entity-for-view model-db entity-id view))
      (assoc :breadcrumbs (breadcrumbs model-db view entity-id))))
