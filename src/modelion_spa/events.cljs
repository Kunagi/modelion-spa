(ns modelion-spa.events
  (:require
   [re-frame.core :as rf]
   [facts-db.api :as db]
   [modelion-spa.navigator :as navigator]))


(def model-db
  (-> (db/new-db)
      (db/++ [
              ;; gesetze-index
              {:db/id "a5ccab6c-2d71-4bde-93f5-3a231d8727e6"
               :module/ident :gesetze-index
               :modeling/name "Gesetze Index"
               :modeling/doc "Liste aller Gesetze. Filterbar."
               :module/resources #{"6566ed48-0e48-4cd7-9028-aec185e7e082"}
               :module/resource-loaders #{"835299de-ce86-4a7c-9ac9-4293a7514dac"}}

              {:db/id "6566ed48-0e48-4cd7-9028-aec185e7e082"
               :modeling/name "Index aller Gesetze"
               :modeling/doc "Liste aller Gesetze aus allen Kontexten."
               :resource/ident :index
               :resource/loader "835299de-ce86-4a7c-9ac9-4293a7514dac"}

              {:db/id "835299de-ce86-4a7c-9ac9-4293a7514dac"
               :ajax-resource-loader/url "/data/gesetze/index.edn"
               :ajax-resource-loader/load-on-startup true
               :ajax-resource-loader/cache-in-browser true
               :ajax-resource-loader/resource "6566ed48-0e48-4cd7-9028-aec185e7e082"}

              ;; gesetze-basis
              {:db/id "a2307ead-0875-4073-aa1c-740d21de0e33"
               :module/ident :gesetze-base
               :modeling/name "Gesetze Basis"
               :modeling/doc "Basis-Entitäten für Gesetze"}

              ;; model
              {:db/id "model:root"
               :modeling/name "Model"
               :model/modules #{"a5ccab6c-2d71-4bde-93f5-3a231d8727e6"
                                 "a2307ead-0875-4073-aa1c-740d21de0e33"}}])))


(rf/reg-event-db
 :modelion/init
 (fn [db _]
   (-> db
       (assoc :modelion/model-db model-db)
       (assoc :modelion/navigator (navigator/new-navigator model-db)))))


(rf/reg-event-db
 :modelion/activate-view
 (fn [db [_ {:keys [view entity-id]}]]
   (.log js/console view entity-id)
   (let [model-db (get db :modelion/model-db)]
     (-> db
         (update :modelion/navigator navigator/activate-view model-db view entity-id)))))
