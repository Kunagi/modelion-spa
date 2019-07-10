(ns modelion-spa.ui-components.module
  (:require
   ["@material-ui/core" :as mui]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [mui-commons.nested-paperscraps :as nps]
   [modelion-spa.ui-components.commons :as c]))


(defn Entity-Data
  [{:keys [entity
           target-view]}]
  [muic/Data entity])


(defn Module
  [module]
  [:div
   [c/Entity-Paper
    {:entity module}
    [:div

     [c/Labeled-Entities-List
      {:label "Resources"
       :entities (-> module :module/resources)
       :expansion-content-component-fn Entity-Data}]

     [c/Labeled-Entities-List
      {:label "AJAX Resource Loaders"
       :entities (-> module :module/ajax-resource-loaders)
       :expansion-content-component-fn Entity-Data}]

     [c/Labeled-Entities-List
      {:label "UI Components"
       :entities (-> module :module/ui-components)
       :expansion-content-component-fn Entity-Data}]]]])

     ;; [c/Labeled
     ;;  {:text "Data"}
     ;;  [muic/Data module]]]]])
    ;[c/Papers-List Module (-> model :model/modules)]]])
  ;; [:hr]
  ;; [nps/Data model]])
