(ns modelion-spa.ui-components.module
  (:require
   ["@material-ui/core" :as mui]
   ["@material-ui/icons" :as icons]
   [reagent.core :as r]
   [re-frame.core :as rf]
   [mui-commons.components :as muic]
   [mui-commons.nested-paperscraps :as nps]
   [modelion-spa.ui-components.commons :as c]))


(defn Entity-Data
  [{:keys [entity
           target-view]}]
  [muic/Data entity])


(defn ExpansionPanelsAttribute
  [label entities details-component-fn]
  [:div
   [c/Label label]
   (into
    [:div]
    (map
     (fn [entity]
       [:> mui/ExpansionPanel
        [:> mui/ExpansionPanelSummary
         {:expand-icon (r/as-element [:> icons/ExpandMore])}
         (-> entity :modeling/name)]
        [:> mui/ExpansionPanelDetails
         [details-component-fn entity]]])
     entities))])


(defn Resource-Details
  [resource]
  (muic/Data resource))


(defn AjaxResourceLoader-Details
  [loader]
  (muic/Data loader))


(defn UiComponent-Details
  [uic]
  (muic/Data uic))


(defn Module
  [module]
  [:> mui/Paper
   {:style {:padding "10px"}}

   [:div
    {:style {:display :grid
             :grid-gap "1em"}}

    [ExpansionPanelsAttribute
     "Resources"
     (-> module :module/resources)
     Resource-Details]

    [ExpansionPanelsAttribute
     "AJAX Resource Loaders"
     (-> module :module/ajax-resource-loaders)
     AjaxResourceLoader-Details]

    [ExpansionPanelsAttribute
     "UI Components"
     (-> module :module/ui-components)
     UiComponent-Details]]])
