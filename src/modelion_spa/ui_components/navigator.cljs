(ns modelion-spa.ui-components.navigator
  (:require
   ["@material-ui/core" :as mui]
   [re-frame.core :as rf]

   [mui-commons.components :as muic]

   [modelion-spa.ui-components.model :refer [Model]]
   [modelion-spa.ui-components.module :refer [Module]]))


(defn Breadcrumb
  [{:keys [goto-event
           text
           type-text]}]
  (if goto-event
    [:> mui/Link
     {:href "#"
      :on-click #(rf/dispatch goto-event)}
     text]
    [:div
     {:style {:font-weight 800}}
     text]))


(defn Breadcrumbs
  [navigator]
  [:> mui/Paper
   {:style {:padding "5px"}}
   (into
    [:> mui/Breadcrumbs
     {:separator "›"}]
    (map
     (fn [breadcrumb]
       [Breadcrumb breadcrumb])
     (-> navigator :breadcrumbs)))])


(defn Navigator
  []
  (let [navigator  @(rf/subscribe [:modelion/navigator])
        view (:view navigator)
        entity (:entity navigator)]
    (if-not navigator
      [:div "Loading..."]
      [:div
       [:h4
        "Modelion Navigator " (str view)]
       [Breadcrumbs navigator]
       [:br]
       (case view
         :model [Model entity]
         :module [Module entity]
         [:div (str "Unsupported view: " view)])])))
