(ns modelion-spa.ui-components.module
  (:require
   ["@material-ui/core" :as mui]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [mui-commons.nested-paperscraps :as nps]
   [modelion-spa.ui-components.commons :as c]))


(defn Module
  [module]
  [:div
   [c/Entity-Paper
    {:entity module}
    [:div

     [c/Labeled-Entities-List
      {:label "Resources"
       :entities (-> module :module/resources)
       :target-view :resource}]

     [c/Labeled-Entities-List
      {:label "AJAX Resource Loaders"
       :entities (-> module :module/ajax-resource-loaders)
       :target-view :ajax-resource-loader}]

     [c/Labeled-Entities-List
      {:label "UI Components"
       :entities (-> module :module/ui-components)
       :target-view :ui-component}]

     [c/Labeled
      {:text "Data"}
      [muic/Data module]]]]])
    ;[c/Papers-List Module (-> model :model/modules)]]])
  ;; [:hr]
  ;; [nps/Data model]])
