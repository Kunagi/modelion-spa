(ns modelion-spa.events
  (:require
   [re-frame.core :as rf]

   [modelion-spa.model-loader :as model-loader]
   [modelion-spa.editor-db :as editor-db]))


(rf/reg-event-db
 :modelion/init
 (fn [db _]
   (let [model-db (model-loader/load-model)]
     (-> db
         (assoc :modelion/model-db model-db)
         (assoc :modelion/editor-db (editor-db/new-editor-db))))))


(rf/reg-event-db
 :modelion/editor-key-pressed
 (fn [db [_ key]]
   (js/console.log "key:" key)
   db))
