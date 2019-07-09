(ns modelion-spa.subscriptions
  (:require
   [re-frame.core :as rf]))


(def model
  {:model/modules
   [{:db/id "module:gesetze-base"
     :module/ident :gesetze-index
     :modeling/name "Gesetze Basis"
     :modeling/doc "Basis-Entitäten für Gesetze"}
    {:db/id "module:gesetze-index"
     :module/ident :gesetze-index
     :modeling/name "Gesetze Index"
     :modeling/doc "Liste aller Gesetze. Filterbar."}]})

(def module
  {:db/id "module:gesetze-index"
   :module/ident :gesetze-index
   :modeling/name "Gesetze Index"
   :modeling/doc "Liste aller Gesetze. Filterbar."})


(rf/reg-sub
 :modelion/navigator-view
 (fn [db _]
   (get-in db [:modelion/navigator-view] :model)))


(rf/reg-sub
 :moelion/navigator-entity
 (fn [db _]
   (case (get-in db [:modelion/navigator-view] :model)
     :model model
     :module module
     nil)))
