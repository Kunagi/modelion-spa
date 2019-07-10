(ns modelion-spa.model-loader
  (:require
   [re-frame.core :as rf]

   [facts-db.api :as db]
   [modelion.api :as modelion]

   [modelion-spa.navigator :as navigator]
   [modelion-spa.modules.gesetze-base :as gesetze-base]
   [modelion-spa.modules.gesetze-index :as gesetze-index]
   [modelion-spa.modules.gesetze-spa :as gesetze-spa]))


(defn- apply-module-events
  [model events]
  (modelion/apply-events model (remove #(= :end %) events)))


(defn load-model
  []
  (-> (modelion/new-model)
      (apply-module-events gesetze-base/module-events)
      (apply-module-events gesetze-index/module-events)
      (apply-module-events gesetze-spa/module-events)))
