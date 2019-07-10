(ns modelion-spa.events
  (:require
   [re-frame.core :as rf]

   [modelion-spa.model-loader :as model-loader]
   [modelion-spa.navigator :as navigator]))


(rf/reg-event-db
 :modelion/init
 (fn [db _]
   (let [model-db (model-loader/load-model)]
     (-> db
         (assoc :modelion/model-db model-db)
         (assoc :modelion/navigator (navigator/new-navigator model-db))))))


(rf/reg-event-db
 :modelion/activate-view
 (fn [db [_ {:keys [view entity-id]}]]
   (.log js/console view entity-id)
   (let [model-db (get db :modelion/model-db)]
     (-> db
         (update :modelion/navigator navigator/activate-view model-db view entity-id)))))
